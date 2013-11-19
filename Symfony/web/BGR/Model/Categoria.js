function Categoria(name, descripcion, id) {
	this.id = id;
	this.name = ko.observable (name);
	this.descripcion = ko.observable (descripcion);
}