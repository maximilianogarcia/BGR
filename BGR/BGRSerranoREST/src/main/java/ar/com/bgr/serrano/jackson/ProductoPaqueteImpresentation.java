package ar.com.bgr.serrano.jackson;


public class ProductoPaqueteImpresentation<T> {

	private Integer id;
	private T data;
	
	public ProductoPaqueteImpresentation(){}
	
	public ProductoPaqueteImpresentation(T data, Integer id) {
		super();
		this.id = id;
		this.data = data;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	
	
}
