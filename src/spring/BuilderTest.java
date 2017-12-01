package spring;

import org.junit.Test;

import java.util.Arrays;

/**
 * 建造者模式
 */
public class BuilderTest {

	@Test
	public void test() {
		Programmer.ProgrammerBuilder builder = new Programmer.ProgrammerBuilder().setFirstName("F").setLastName("L").setCity("City").setZipCode("0000A").setAddress("Street 39").setLanguages(new String[]{"bash", "Perl"}).setProjects(new String[]{"Linux kernel"});
		Programmer programmer = builder.build();
		System.out.println(programmer.toString());
	}
}

class Programmer {
	@Override
	public String toString() {
		return "Programmer{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				", zipCode='" + zipCode + '\'' +
				", city='" + city + '\'' +
				", languages=" + Arrays.toString(languages) +
				", projects=" + Arrays.toString(projects) +
				'}';
	}

	private String firstName;

	private String lastName;

	private String address;

	private String zipCode;

	private String city;

	private String[] languages;

	private String[] projects;

	public Programmer(String firstName, String lastName, String address, String zipCode, String city, String[] languages, String[] projects) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.languages = languages;
		this.projects = projects;
	}

	public static class ProgrammerBuilder {
		private String firstName;

		private String lastName;

		private String address;

		private String zipCode;

		private String city;

		private String[] languages;

		private String[] projects;

		public ProgrammerBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public ProgrammerBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public ProgrammerBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		public ProgrammerBuilder setZipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}

		public ProgrammerBuilder setCity(String city) {
			this.city = city;
			return this;
		}

		public ProgrammerBuilder setLanguages(String[] languages) {
			this.languages = languages;
			return this;
		}

		public ProgrammerBuilder setProjects(String[] projects) {
			this.projects = projects;
			return this;
		}

		public Programmer build() {
			return new Programmer(firstName, lastName, address, zipCode, city, languages, projects);
		}
	}

}
