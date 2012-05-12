package tdb.search.ejb;

import tdb.search.entity.Company;

public class Converter {

	public static CompanyResult company(Company in) {
		CompanyResult out = new CompanyResult();
		out.setAddress(in.getAddress());
		out.setCode(in.getCode());
		out.setName(in.getName());
		out.setType(in.getType());
		return out;
	}

	public static CompanyResult[] company(Company[] in) {
		CompanyResult[] out = new CompanyResult[in.length];

		for(int i=0; i < out.length; i++) {
			out[i] = company(in[i]);
		}
		return out;
	}

	public static Company companyResult(CompanyResult in) {
		Company out = new Company();
		out.setAddress(in.getAddress());
		out.setCode(in.getCode());
		out.setName(in.getName());
		out.setType(in.getType());
		return out;
	}

	public static Company[] companyResult(CompanyResult[] in) {
		Company[] out = new Company[in.length];

		for(int i=0; i < out.length; i++) {
			out[i] = companyResult(in[i]);
		}
		return out;
	}


}
