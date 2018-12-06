package yellow.pojo;

public class User {
    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }			   //anonymousName
	public String getAnonymousName() {
		// TODO Auto-generated method stub
		if (getName().length() > 2) {
			if (getName().length() > 3) {
				char[] ch = getName().toCharArray();
				for (int i = 1; i < ch.length - 2; i++) {
					ch[i] = '*';
				}
				return new String(ch);
			}
			char ch[] = getName().toCharArray();
			ch[2] = '*';
			return new String(ch);
		}
		return getName();
	}
}