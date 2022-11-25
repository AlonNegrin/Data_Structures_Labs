package Lab03Test;

public class ListTestInteger extends ListTest<Integer>{
	private int i=1;
	@Override
	public Integer getParameterInstance() {
		return i++;
	}

}
