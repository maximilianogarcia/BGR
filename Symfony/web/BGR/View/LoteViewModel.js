function LoteViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.lotes = ko.mapping.fromJS([new Lote()]);
   self.selected = ko.mapping.fromJS(new Lote());
   self.createNew = ko.observable(false);
   self.step1 = ko.observable(true);
   self.step2 = ko.computed(function(){
        return !self.step1();
   });


   self.productos = ko.observableArray();

   self.selectedProductoId = ko.observable(null);
 
   self.hayProductoSeleccionado = ko.computed(function(){
     return self.selectedProductoId() != null;
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
  


    var $myForm = $('#editLoteForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax(BASE_REST_URL+"/lote/save", {
                data: {'data': JSON.stringify(serializado) },
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#editLote').modal('hide');
                  self.lotes
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
      var $myForm = $('#editLoteForm');
      if ($myForm[0].checkValidity()) {
          $.ajax(BASE_REST_URL+"/lote/update", {
                  data: {'data': JSON.stringify(serializado) },
                  type: "PUT",
                  success: function(result) {
                    //self.selectedUnmapped.name(result.producto.name);
                    self.selectedUnmapped.producto.precio_venta(result.producto.precio_venta);
                    self.selectedUnmapped.producto.precio_compra(result.producto.precio_compra);
                    self.selectedUnmapped.producto.id(result.producto.id);
                    self.selectedUnmapped.producto.name(result.producto.name);
                    self.selectedUnmapped.descripcion(result.descripcion);

                    $('#editLote').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(){
     $.ajax(BASE_REST_URL+"/lote/getAll", {
            type: "GET",
            success: function(result) {
                  ko.mapping.fromJS(result, self.lotes
              );
            }
      });
   }

   self.desactivar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     $.ajax(BASE_REST_URL+"/lote/desactivar", {
            data: {'data': serializado.id},
            type: "DELETE",
            success: function(result){
               $('#editLote').modal('hide');
               self.lotes.remove(self.selectedUnmapped);               
            },
            error: function(error){
					alert(error.responseText);            
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
      $('#editLote').modal('show');
   }


   self.create = function(data){

      self.selectedProductoId(null);
      self.createNew(true);
      self.step1(true);

      self.selectedUnmapped = data;
      ko.mapping.fromJS(new Lote, self.selected);
      $('#editLote').modal('show');
   }

   self.next = function(data){
      self.step1(false);

   }
   self.back = function(data){
      self.step1(true);

   }

}