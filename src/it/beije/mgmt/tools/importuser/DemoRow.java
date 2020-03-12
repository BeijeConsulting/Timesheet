package it.beije.mgmt.tools.importuser;

public class DemoRow {

	private int _id;
	private String _value;

	public int GetID() { return _id; }
	public void SetID(int id) { _id = id; }
	
	public String GetValue() { return _value; }
	public void SetValue(String value) { _value = value; }
	
	public DemoRow()
	{
		_id = 0;
		_value = "";
	}
	public DemoRow(int id, String value)
	{
		_id = id;
		_value = value;
	}
}
