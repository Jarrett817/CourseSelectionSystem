package entity;

public class Course {
    private int id; //课程编号
    private String name; // 课程名
    private int teacherId;  //任课老师id
    private String coursePlace; // 上课地点
    private String coDate; //上课时间
    private int maxNum; //课程容量
    private int selectedNum; //已选人数

    @Override
    public int hashCode() {
        return this.getId() + this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Course) {
            Course c = (Course) obj;
            if (this.getId() == c.getId() && this.getName().equals(c.getName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }

    public String getCoDate() {
        return coDate;
    }

    public void setCoDate(String coDate) {
        this.coDate = coDate;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(int selectedNum) {
        this.selectedNum = selectedNum;
    }
}
