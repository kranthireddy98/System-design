package org.example.Creational.builder;

public class UserBp {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;
    private final String country;

    private UserBp(UserBuilder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email=builder.email;
        this.age = builder.age;
        this.country =builder.country;
    }

    public static class UserBuilder{
        private String firstName;
        private String lastName;
        private String email;
        private Integer age;
        private String country;

        public UserBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public UserBuilder email(String email){
            this.email = email;
            return this;
        }
        public UserBuilder age(Integer age){
            this.age = age;
            return this;
        }
        public UserBuilder country(String country){
            this.country = country;
            return this;
        }

        public UserBp build(){
            validate();
            return new UserBp(this);
        }

        private void validate(){
            if(email == null || !email.contains("@")){
                throw new IllegalStateException("Invalid email");
            }
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }
}
