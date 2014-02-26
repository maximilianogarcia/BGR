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
	//$.ajaxSetup({traditional:true });
	self.doGet = function(path, pData, callback, errorCallback){
	     $.ajax(BASE_REST_URL+path, {
	    	   data: pData,
	           type: "GET",
		       success: function(result) {
	                callback(result);
	           },
	           error: function(error){
	        	   errorCallback(error);
	           }
	      });
	}
	
	
	self.doPost = function(pData, callback,path){
	     $.ajax(BASE_REST_URL+path, {
	    	   data: {'data': pData},
	           type: "POST",
		            success: function(result) {
	                callback(result);
	            }
	      });
	}
	
	
}