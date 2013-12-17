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


   self.productos = ko.observableArray();
   self.lotes = ko.observableArray();
   self.materiales = ko.observableArray();
   self.medidas = ko.observableArray();

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
       var self = this;
       return ko.utils.arrayFirst(this.productos(), function(producto) {
           return self.selectedProductoId() == producto.id;
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


  self.getLotesFor = function(producto){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/getLotesFor", {
            type: "GET",
            data: {'data': producto},
            success: function(result) {
                  ko.mapping.fromJS(result, self.presentaciones
              );
            }
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
      self.createNew(false);
      self.step1(true);
      self.selectedUnmapped = data;
      self.selected.producto(null);
      ko.mapping.fromJS(data, self.selected);
      $('#editPresentacion').modal('show');
   }


   self.create = function(data){

      self.selectedProductoId(null);
      self.createNew(true);
      self.step(1) ;

      self.selectedUnmapped = data;
      ko.mapping.fromJS(new Presentacion, self.selected);
      $('#editPresentacion').modal('show');
   }

   self.doNext = function(){
    self.step(self.step()+1);
   }
   self.next = function(paso,data){
      
      switch(paso)
      {
      case "lote":

         self.getLotesFor(self.selectedProducto());
         break;
      case "material":
        alert("material");        
        break;
      default:
        alert("default");
      }

   }
   self.back = function(data){
      self.step(self.step()-1);
   }

}