package tdb.search.ejb;

import static org.junit.Assert.*;

import org.junit.Test;

import tdb.search.entity.Company;
import tdb.search.entity.Key;

public class ConverterTest {

	@Test
	public void testCompanyCompany() {
		Company in = new Company();
		in.setAddress("住所");
		in.setCode("1");
		in.setName("名前");
		in.setType("種別");
		in.getKeys().add(new Key("キーワード"));

		CompanyResult out = Converter.company(in);

		assertEquals(in.getAddress(), out.getAddress());
		assertEquals(in.getCode(), out.getCode());
		assertEquals(in.getName(), out.getName());
		assertEquals(in.getType(), out.getType());

	}

	@Test
	public void testCompanyCompanyArray() {
		Company in = new Company();
		in.setAddress("住所");
		in.setCode("1");
		in.setName("名前");
		in.setType("種別");
		in.getKeys().add(new Key("キーワード"));

		Company[] ins = new Company[1];
		ins[0] = in;

		CompanyResult[] out = Converter.company(ins);

		assertEquals(in.getAddress(), out[0].getAddress());
		assertEquals(in.getCode(), out[0].getCode());
		assertEquals(in.getName(), out[0].getName());
		assertEquals(in.getType(), out[0].getType());
	}

	@Test
	public void testCompanyResultCompanyResult() {
		CompanyResult in = new CompanyResult();
		in.setAddress("住所");
		in.setCode("1");
		in.setName("名前");
		in.setType("種別");

		Company out = Converter.companyResult(in);

		assertEquals(in.getAddress(), out.getAddress());
		assertEquals(in.getCode(), out.getCode());
		assertEquals(in.getName(), out.getName());
		assertEquals(in.getType(), out.getType());
	}

	@Test
	public void testCompanyResultCompanyResultArray() {
		CompanyResult in = new CompanyResult();
		in.setAddress("住所");
		in.setCode("1");
		in.setName("名前");
		in.setType("種別");

		CompanyResult[] ins = new CompanyResult[1];
		ins[0] = in;

		Company[] out = Converter.companyResult(ins);

		assertEquals(in.getAddress(), out[0].getAddress());
		assertEquals(in.getCode(), out[0].getCode());
		assertEquals(in.getName(), out[0].getName());
		assertEquals(in.getType(), out[0].getType());
	}

}
