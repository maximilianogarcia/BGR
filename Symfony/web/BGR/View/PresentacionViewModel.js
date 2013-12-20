function PresentacionViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.presentaciones = ko.mapping.fromJS([new Presentacion()]);
   self.selected = ko.mapping.fromJS(new Presentacion());
   self.createNew = ko.observable(false);

   self.step = ko.observable(1);
   self.step1 = ko.computed(function(){
        return self.step() == 1;
   });
   self.step2 = ko.computed(function(){
        return self.step() == 2;
   });
   self.step3 = ko.computed(function(){
        return self.step() == 3;
   });
   self.step4 = ko.computed(function(){
        return self.step() == 4;
   });
   self.step5 = ko.computed(function(){
        return self.step() == 5;
   });
   self.backeable = ko.computed(function(){
        return self.step() != 1  ;
   });


   self.productos = ko.observableArray();
   self.lotes =  ko.mapping.fromJS([new Lote()]);

   self.materiales = ko.mapping.fromJS([new Material()]);
   self.medidas    = ko.mapping.fromJS([new UnidadMedida()]);

   self.selectedProductoId = ko.observable(null);
   self.selectedMedidaId = ko.observable(null);
   self.selectedLoteId = ko.observable(null);
   self.selectedMaterialId = ko.observable(null);

 
   self.hayProductoSeleccionado = ko.computed(function(){
     return self.selectedProductoId() != null;
   });
   self.hayLoteSeleccionado = ko.computed(function(){
     return self.selectedLoteId() != null;
   });
   self.hayMaterialSeleccionado = ko.computed(function(){
     return self.selectedMaterialId() != null;
   });
   self.hayMedidaSeleccionado = ko.computed(function(){
     return self.selectedMedidaId() != null;
   });

   self.selectedProducto = function() {
   //    var self = this;
       return ko.utils.arrayFirst(this.productos(), function(producto) {
           return self.selectedProductoId() == producto.id;
       });
   }.bind(this);
   self.selectedLote = function() {
  //    var self = this;
       return ko.utils.arrayFirst(this.lotes(), function(lote) {
           return self.selectedLoteId() == lote.id;
       });
   }.bind(this);
   self.selectedMaterial = function() {
   //    var self = this;
       return ko.utils.arrayFirst(this.materiales(), function(material) {
           return self.selectedMaterialId() == material.id;
       });
   }.bind(this);

   self.selectedUnidad_de_medida = function() {
      // var self = this;
       return ko.utils.arrayFirst(this.medidas(), function(um) {
           return self.selectedMedidaId() == um.id;
       });
   }.bind(this);

   self.apply = function(){
     ko.applyBindings(self);
     self.getAll();
     var viewModelProducto = new ProductoViewModel();
     viewModelProducto.getAll(self.copiar);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.copiar =function(destino){
        self.productos(destino);
   }


   self.save = function(){
    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.producto = self.selectedProducto();
    serializado.lote = self.selectedLote();
    serializado.lote.fecha_de_elaboracion= moment(serializado.lote.fecha_de_elaboracion).format('YYYY-MM-DD');
    serializado.lote.fecha_de_vencimiento= moment(serializado.lote.fecha_de_vencimiento).format('YYYY-MM-DD');
    serializado.date_create= moment().format('YYYY-MM-DD');
    serializado.date_update= moment().format('YYYY-MM-DD');


    serializado.material = self.selectedMaterial();
    serializado.unidad_de_medida = self.selectedUnidad_de_medida();
  


    var $myForm = $('#editPresentacionForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/presentacion/save", {
                data: {'data': JSON.stringify(serializado) },
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#editPresentacion').modal('hide');
                  self.presentaciones
            .push(ko.mapping.fromJS(result)); 
                }
        });
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.producto = self.selectedProducto();
    serializado.lote = self.selectedLote();
    serializado.lote.fecha_de_elaboracion= moment(serializado.lote.fecha_de_elaboracion).format('YYYY-MM-DD');
    serializado.lote.fecha_de_vencimiento= moment(serializado.lote.fecha_de_vencimiento).format('YYYY-MM-DD');
    serializado.date_create= moment().format('YYYY-MM-DD');
    serializado.date_update= moment().format('YYYY-MM-DD');


    serializado.material = self.selectedMaterial();
    serializado.unidad_de_medida = self.selectedUnidad_de_medida();
  
      var $myForm = $('#editPresentacionForm');
      if ($myForm[0].checkValidity()) {
          $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/presentacion/update", {
                  data: {'data': JSON.stringify(serializado) },
                  type: "PUT",
                  success: function(result) {
                    self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.precio_venta(result.precio_venta);
                    self.selectedUnmapped.precio_compra(result.precio_compra);
                    self.selectedUnmapped.producto.id(result.producto.id);
                    self.selectedUnmapped.producto.name(result.producto.name);
                    self.selectedUnmapped.producto.descripcion(result.descripcion);

                    $('#editPresentacion').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/presentacion/getAll", {
            type: "GET",
            success: function(result) {
                  ko.mapping.fromJS(result, self.presentaciones
              );
            }
      });
   }


  self.getLotesByProducto = function(producto){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/lote/getLotesByProducto", {
            type: "POST",
            data: {'data': JSON.stringify(producto)},
            success: function(result) {
               self.lotes(result);
               self.doNext(
                  function(){
                    self.selectedLoteId(self.selectedUnmapped.lote.id());
                  }
               );
            }
      });
   }

  self.getMaterialesByProducto = function(producto){

     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/material/getMaterialesByProducto", {
            type: "POST",
            data: {'data': JSON.stringify(producto)},
            success: function(result) {
               self.materiales(result);
               self.doNext(
                  function(){
                    self.selectedMaterialId(self.selectedUnmapped.material.id());
                  }
                );
            }
      });

  }
  self.getMedidasByProducto = function(producto){
       self.medidas(producto.unidad_de_medidas);

       self.doNext(
         function(){
            self.selectedMedidaId(self.selectedUnmapped.unidad_de_medida.id());
       });
  }



   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     serializado.producto(self.selectedProducto);
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/presentacion/delete", {
            data: {'data': serializado},
            type: "DELETE",
            success: function(result){
               $('#editPresentacion').modal('hide');
               self.presentaciones
          .remove(self.selectedUnmapped);
            }
      });
   }

   self.editar = function(data){
      self.selectedProductoId(data.producto.id());

      self.selectedMedidaId(data.unidad_de_medida.id());
      self.selectedLoteId(data.lote.id());
      self.selectedMaterialId(data.material.id());

      self.createNew(false);
      self.step(1);
      self.selectedUnmapped = data;
      self.selected.producto(null);
      ko.mapping.fromJS(data, self.selected);
      $('#editPresentacion').modal('show');
   }


   self.create = function(data){

      self.selectedProductoId(null);
      self.selectedMedidaId(null);
      self.selectedLoteId(null);
      self.selectedMaterialId(null);

      self.createNew(true);
      self.step(1) ;

      self.selectedUnmapped = data;
      ko.mapping.fromJS(new Presentacion, self.selected);
      $('#editPresentacion').modal('show');
   }

   self.doNext = function(callback){
    self.step(self.step()+1);
    callback();
   }
   self.next = function(paso,data){
      
      switch(paso)
      {
      case "lote":

         self.getLotesByProducto(self.selectedProducto());
         break;
      case "material":
         self.getMaterialesByProducto(self.selectedProducto());
        break;
      case "medida":
         self.getMedidasByProducto(self.selectedProducto());
        break;
      case "form":
         self.doNext();
        break;
      default:
        alert("Error inesperado de inesperanza total");
      }

   }
   self.back = function(data){
      self.step(self.step()-1);
   }

}