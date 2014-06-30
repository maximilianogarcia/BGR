/**
* soporte extendido para JSON ajax_request.
* 
* @author matias.garcia
*/
function _ajax_request(contentType, url, data, callback, dataType, method) {
if ($.isFunction(data)) {callback = data;data = {};}
return $.ajax({contentType:contentType,url:url,data:data,success:callback,dataType:dataType,type:method});
}

$.extend({postJSON:function(url, data, callback, type) {
return _ajax_request('application/json', url, data, callback, 'json', 'POST');}
});

$.extend({deleteJSON:function(url, data, callback, type) {
return _ajax_request('application/json', url, data, callback, 'json', 'DELETE');}
});

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
	
	self.doDelete = function(path, callback, errorCallback){
	     $.ajax(BASE_REST_URL+path, {
	           type: "DELETE",
		       success: function(result) {
	                callback(result);
	           },
	           error: function(error){
	        	   errorCallback(error);
	           }
	      });
	}	
	
	
	self.pushInGrid  = function(data, grid) {
		var id = data.id;
		grid.push(data);
	}
	self.removeFromGrid  = function(data, grid) {
		var id = data.id();
		var innerArray = grid();
		
	    for (var i = 0, len = innerArray.length; i < len; ++i) {
	        var item = innerArray[i];
	        if(item.id == id){
	        	grid.remove(item);	        	
	        }
	    }		
	}
	
	self.updateGrid= function(data, grid) {
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

//fix to HTML5 datePicker bug onChange
function closeMe(val){
	$(val).attr("type", "text");
	$(val).attr("type", "date");
 }
