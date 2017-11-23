package com.vn.hcmute.team.cortana.mymoney.bean;

public class Person {

    private String person_id;
    private String name;
    private String describe;
    private String user_id;

    public Person() {
        this.person_id = "";
        this.name = "";
        this.describe = "";
        this.user_id = "";
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((person_id == null) ? 0 : person_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        if (person_id == null) {
            if (other.person_id != null) {
                return false;
            }
        } else if (!person_id.equals(other.person_id)) {
            return false;
        }
        return true;
    }

}
