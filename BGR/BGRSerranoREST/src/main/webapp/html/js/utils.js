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
	
	
	self.doPost = function(path, pData, callback, errorCallback){
	     $.ajax(BASE_REST_URL+path, {
	    	   data: {'data': pData},
	           type: "POST",
		       success: function(result) {
	                callback(result);
	           },
	           error: function(error){
	        	   errorCallback(error);
	           }
	      });
	}
	
	self.putInGrid = function(data, grid) {
		var id = data.id;
		var innerArray = grid();
		
	    for (var i = 0, len = innerArray.length; i < len; ++i) {
	        var item = innerArray[i];
	        if(item.id == id){
	        	grid.replace(grid()[i], data);	        	
	        }
	    }		
	}
}