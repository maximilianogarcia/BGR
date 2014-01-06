function UnidadMedida() {
	this.id = ko.observable ();
	this.name = ko.observable ();
	this.descripcion = ko.observable ();
    this.equivalencia = ko.observable ();
    this.divisible= ko.observable (false);
    this.deriva_de= ko.observable();
    
}