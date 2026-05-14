package tests.model;

public class AccountModel {

        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private Integer age;
        private boolean isActive;
        private double balance;
        private AddressModel address;
        private PhoneModel phoneNumber;
        private String createdAt;
        private String updatedAt;

        public Integer getId() {
                return id;
        }
        public void setId (Integer id) {
                this.id = id;
        }

        public String getFirstName() {
                        return firstName;
        }
        public void setFirstName (String firstName){
                        this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }
        public void setLastName (String lastName) {
                this.lastName = lastName;
        }

        public String getEmail() {
                return email;
        }
        public void setEmail (String email) {
                this.email = email;
        }

        public Integer getAge() {
                return age;
        }
        public void setAge (Integer age) {
                this.age = age;
        }

        public boolean getIsActive() {
                return isActive;
        }
        public void setIsActive (boolean isActive) {
                this.isActive = isActive;
        }

        public double getBalance() {
                return balance;
        }
        public void setBalance (double balance) {
                this.balance = balance;
        }

      public AddressModel getAddress() {
                return address;
      }
      public void setAddress(AddressModel address) {
                this.address = address;
      }

      public PhoneModel getPhoneNumber() {
                return phoneNumber;
      }
      public void setPhoneNumber(PhoneModel phoneNumber) {
                this.phoneNumber = phoneNumber;
      }

      public String getCreatedAt() {
                return createdAt;
      }
      public void setCreatedAt (String createdAt) {
                this.createdAt = createdAt;
      }

      public String getUpdatedAt() {
                return updatedAt;
      }
      public void setUpdatedAt (String updatedAt) {
                this.updatedAt = updatedAt;
      }
}
