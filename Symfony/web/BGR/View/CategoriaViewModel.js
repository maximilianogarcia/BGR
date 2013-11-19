function CategoriaViewModel() {
   self = this;
   self.categoria = new Categoria("uno","dos");

   self.apply = function(){
 	  ko.applyBindings(self);
   }
   
   self.serialized = function(){
      return  ko.toJSON(self.categoria);
   }

   self.submit = function(){
 	  $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/categoria/save", {
            data: {'data': self.serialized() },         
            type: "post",
            beforeSend: function(){alert ( self.serialized() )},
            success: function(result) { alert(result) }
      });
   }
}

