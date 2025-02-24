package com.app.SignupDto;

public class SignInDto {
	 private String mobileNo;
	    private String otp;
	
	    public SignInDto(Long Id, String fullName, String dOB, String email,String mobileNo, String otp) 
		{
			this.mobileNo = mobileNo;
			this.otp = otp;
		}
		
		public SignInDto ()
		{
			
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		} 

}