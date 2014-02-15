function Utils(){
	var self = this; 
	self.getAll = function(callback,path){
	     $.ajax(BASE_REST_URL+path, {
	           type: "GET",
		            success: function(result) {
	                callback(result);
	            }
	      });
	}
	
	self.doPost = function(callback,path){
	     $.ajax(BASE_REST_URL+path, {
	           type: "POST",
		            success: function(result) {
	                callback(result);
	            }
	      });
	}
	
	
}