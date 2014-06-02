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
	
	self.doDelete = function(path, pData, callback, errorCallback){
	     $.ajax(BASE_REST_URL+path, {
	           type: "DELETE",
	           data:{'data':pData},
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
	
	self.fadeVisible = {
			init : function(element, valueAccessor) {
				// Initially set the element to be instantly visible/hidden
				// depending on the value
				var value = valueAccessor();
				$(element).toggle(ko.unwrap(value)); // Use "unwrapObservable" so
														// we can handle values that
														// may or may not be
														// observable
			},
			update : function(element, valueAccessor) {
				// Whenever the value subsequently changes, slowly fade the element
				// in or out
				var value = valueAccessor();
				ko.unwrap(value) ? $(element).fadeIn() : $(element).fadeOut();
			}
		};

}