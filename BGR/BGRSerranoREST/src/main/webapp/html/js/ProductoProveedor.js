function ProductoProveedor() {
	this.producto_id = ko.observable ();
	//this.proveedor_id = ko.observable ();
	this.proveedor = ko.observable ( new Proveedor());
	this.producto = ko.observable ( new Producto());
	this.precio_reposicion = ko.observable ();
	this.cantidad = ko.observable ();
}