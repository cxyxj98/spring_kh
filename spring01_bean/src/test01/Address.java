package test01;

public class Address {

		private String name;
		private String address;
		private String phone;
		
		public Address() {
			System.out.println("Adress의 기본 생성자 호출");
		}
		
		public Address(String name, String address, String phone) {
			super();
			this.name = name;
			this.address = address;
			this.phone = phone;
			
			System.out.println("Address 매개변수가 있는 생성자 호출되었습니다.");
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
			System.out.println("setName 호출");
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
			System.out.println("setAddress 호출");
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
			System.out.println("setPhone 호출");
		}
		@Override
		public String toString() {
			return "Address [name=" + name + ", address=" + address + ", phone=" + phone + "]";
		}
		
}
