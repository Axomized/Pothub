package adminSearch;

public interface SearchObject {
	public String getExecutableSQL();
	public void setLimits(int start, int end);
}
