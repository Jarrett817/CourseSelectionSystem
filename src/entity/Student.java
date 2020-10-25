package entity;

public class Student {
    private int id;
    private String number;//ѧ��
    private String name;
    private String passWord;
    private String sex = "男";
    private int clazz_id;

    public int getClazz_id() {
        return clazz_id;
    }

    public void setClazz_id(int clazz_id) {
        this.clazz_id = clazz_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
