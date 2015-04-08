package dataBase;


public class Company extends Client{


	public Company(DataBase db, String fullName, String address, String state) {
		super(db);
		this.isPerson = false;
	}

	/*
	 * Add Company specific data Called from super
	 */
	@Override
	protected void addSubData() {
		// TODO Auto-generated method stub
		
	}





}
