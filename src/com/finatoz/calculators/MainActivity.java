/*
 * This code is for calculating the Compound Interest with monthly deposits
 * Compounding has the option of Monthly(n=12),Annually(n=1)
 * 
 * 
 * ********************************************************************************************************************************
 *  For monthly deposit with compounding yearly 
 *  Amount			=P*(1+r/n)^nt +Y* (((1+r/n)^nt-1))/((1+r/n)-1))
 *  
 *  Maturity value is 												Amount
 *  Principal Amount(Initial Investment) is							P
 *  Rate of interest in percentage so r		=						rate/100
 *  Period is given in years so t			=						t  years
 *  Monthly Deposit(SIP) is 										M 
 *  Compounding for yearly n				=						1
 *  Y is sum of monthly deposits plus partial interest which is
 *  Y		=12*M + 6.5*M*r
 *  ********************************************************************************************************************************
 * 
 * 
 * *********************************************************************************************************************************
 *  For monthly deposit with compounding monthly
 * 	Amount			=P*(1+r/n)^nt + M*(((1+r/n)^nt-1)/((1+r/n)-1))
 * 
 *  Maturity value is 												Amount
 *  Principal Amount(Initial Investment) is							P
 *  Rate of interest in percentage so r		=						rate/100
 *  Period is given in years so t			=						t  years
 *  Monthly Deposit(SIP) is 										M 
 *  Compounding for monthly n				=						12
 * **********************************************************************************************************************************
 *
 *The result will be in the form of 
 *Principal Invested: Initial Investment + Monthly Deposit*12*Period(years)
 *Interest Earned	: Maturity Value - Principal Invested
 *Maturity Value	: Amount
 *
 */

//*************************************************************************************************************
//*************************************************************************************************************
//  New Changes-
//  Now a new feature is added that will have the option of incrementing the monthly deposit
//  by some percentage.
//*************************************************************************************************************
//*************************************************************************************************************





package com.finatoz.calculators;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	/*
	 * Declare all the variables 
	 * double for getting values,
	 * string for taking a string value from spinner(drop down)
	 * Edit Text for taking user inputs,
	 * Spinner for drop down menu
	 * buttons for calculate and reset
	 */
	
	// constants used when saving and restoring state
	
/*	private  static final String PRINCIPAL_AMOUNT       	= "PRINCIPAL_AMOUNT";
	private  static final String MONTHLY_DEPOSIT        	= "MONTHLY_DEPOSIT";
	private  static final String PERIOD                 	= "PERIOD";
	private  static final String ANNUAL_INTEREST_RATE  		= "PRINCIPAL_AMOUNT";
	private  static final String COMPOUNDING       			= "COMPOUNDING";
*/	
	
	
	
	//doubles value for calculation
	
	public double initialInvestment;
	public double monthlySIP;                     //means monthly deposit
	public double monthlyDepositAnnualRate;                           //annual rate for monthly deposit
	public double period;            			  //period in years
	public double annualInterestRate;
	public double compounding;
	public double maturityValue;
	public double principalInvested;
	public double interestEarned;
	public double Y;                              //sum of monthly deposits plus partial interest which is
	                                              //Y		=12*M + 6.5*M*r  used in monthly deposit with compounding yearly
	
	
	//string value for compounding interest(a spinner or drop down list)
	
	public String compoundingString;
	
	
	//EditText declaration of variables
	
	public EditText initialInvestmentEditText;
	public EditText monthlySIPEditText;
	public EditText periodEditText;
	public EditText annualInterestRateEditText;
	public EditText monthlyDepositAnnualRateEditText;              //this is used as rate for monthly SIP interest rate
	
	
	//Spinner declaration of variables
	
	public Spinner compoundingSpinner;
	
	
	
	//TextView declaration of variables
	
/*	private TextView principalAmountTextView;
	private TextView monthlySIPTextView;
	private TextView periodTextView;
	private TextView anuualInterestRateTextView;
	private TextView compoundingTextView;
*/	
	
	
	//Textview declaration of variables for dislpalying the result
	
	public TextView principalInvestedTextViewDisplay;
	public TextView interestEarnedTextViewDisplay;
	public TextView maturityValueTextViewDisplay;
    public TextView monthlyDepositAnnualRateTextViewDisplay;    // this is for monthly SIP  interest rate
	
	
	
	
    //Button declaration of variables
	
	public Button resetButton;
	public Button calculateButton;
	
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		setTitle("Finatoz:SIP Calculator");
		getReferences(); 		
	
		
		compoundingSpinnerImplementation();
		
		
		//attach listener to EditText fields
		
		initialInvestmentEditText.addTextChangedListener(initialInvestmentEditTextTextWatcher);
		monthlySIPEditText.addTextChangedListener(monthlySIPEditTextTextWatcher);
		
	
		
		
	//	 getValuesFromReferences();
	//	 getValuesFromReferences();
		
	    
		onButtonClick(); 
		//it will call 
		//getValuesFromReferences();
		//displayResult();
		
	}
	
	
	
//	private void principalAmountEditTextTextWatcherImplementation(){
		
	
		
	//	 principalAmountEditText.addTextChangedListener(principalAmountEditTextTextWatcher);
	
	     TextWatcher   initialInvestmentEditTextTextWatcher	=new TextWatcher() {
		
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			
				insertCommaIntoEditText(initialInvestmentEditText,s);
				
				
				
			}
		};
		
		
		
		TextWatcher monthlySIPEditTextTextWatcher	=new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
					
				insertCommaIntoEditText(monthlySIPEditText,s);
				
			}
		};
	
	
	/*
	 * insertCommaIntoEditText is for adding commas into 
	 * EditTextFields during typing the EditText
	 * function is copied doesn't know much about it.
	 * 	
	 */
		
		
		
	
		private void insertCommaIntoEditText(EditText etText,CharSequence s)
	    {
			
	        try {        
	        	
	            if (s.toString().length() > 0) 
	            {
	                String convertedStr = s.toString();
	                if (s.toString().contains(".")) 
	                {
	                    if(chkConvert(s.toString()))
	                        convertedStr = customFormat("###,###.##",Double.parseDouble(s.toString().replace(",","")));
	                } 
	                else
	                {
	                    convertedStr = customFormat("###,###.##", Double.parseDouble(s.toString().replace(",","")));
	                }

	                if (!etText.getText().toString().equals(convertedStr) && convertedStr.length() > 0) {
	                    etText.setText(convertedStr);
	                    etText.setSelection(etText.getText().length());
	                }
	            }

	        } catch (NullPointerException e) {
	            e.printStackTrace();
	        }
	    }

	    public String customFormat(String pattern, double value) {
	        DecimalFormat myFormatter = new DecimalFormat(pattern);
	        String output = myFormatter.format(value);
	        return output;
	    }

	    public boolean chkConvert(String s)
	    {
	        String tempArray[] = s.toString().split("\\.");
	        if (tempArray.length > 1) 
	        {
	            if (Integer.parseInt(tempArray[1]) > 0) {
	                return true;
	            }
	            else 
	                return false;
	        }
	        else
	            return false;
	    }
	
		
	
	
	/*
	 * getReferences is for getting a handle(or pointer) for the
	 * editText fields,
	 * Spinners,
	 * Calculate and reset buttons
	 */	
	public void getReferences(){
		
		      //Get references of EditText fields by referring their ID's
		
				initialInvestmentEditText			=(EditText) findViewById(R.id.initialInvestmentEditText);
				monthlySIPEditText				    =(EditText) findViewById(R.id.monthlySIPEditText);
				periodEditText						=(EditText) findViewById(R.id.periodEditText);
				annualInterestRateEditText			=(EditText) findViewById(R.id.annualInterestRateEditText); 
				monthlyDepositAnnualRateEditText	=(EditText) findViewById(R.id.monthlyDepositAnnualRateEditText);
				
			  //Get reference  of spinner field by referring it's ID
				
				compoundingSpinner					=(Spinner) findViewById(R.id.compoundingSpinner);
				
				
			  //Get references from Button fields  by referring their ID's
				
				resetButton							=(Button) findViewById(R.id.resetButton);
				calculateButton						=(Button) findViewById(R.id.calculateButton);
				
				
			  //Get references from TextView fields(which display results) by referring their ID's
				
				principalInvestedTextViewDisplay	=(TextView)findViewById(R.id.principalInvestedTextViewDisplay);
				interestEarnedTextViewDisplay		=(TextView)findViewById(R.id.interestEarnedTextViewDisplay);
				maturityValueTextViewDisplay		=(TextView)findViewById(R.id.maturityValueTextViewDisplay);
				  
				
				
			//	resetButton.setBackgroundColor(Color.BLUE);
					
		}
	
	
	
	
	
	/*
	 * getValuesFromReferences() is to get the user inputs 
	 * from the handle created by getReferences() and converts into double 
	 */
	public void getValuesFromReferences(){
		
		
		   //Get text from PrincipalAmountEditText handle by getText() change it to string by toString()
		   // parse it to double and save in principalAmount
		   //matches("") is for checking whether the value is null 
		
	//	 principalAmountEditText.getText().toString().replace(",","");
	//	  str			= principalAmountEditText.getText().toString().replace(",","");
		
		   if(!initialInvestmentEditText.getText().toString().matches("")){
			  
			   initialInvestment				=Double.parseDouble(initialInvestmentEditText.getText().toString().replace(",",""));;
			}
		   
		   //if the value is null than assign it to zero
		   else if(initialInvestmentEditText.getText().toString().matches("")){
			   initialInvestment				=0;
			   
		   }
		   
		  
		   
		   //Get text from monthlySIPEditText handle by getText() change it to string by toString()
		   // parse it to double and save in monthlySIP
		   //matches("") is for checking whether the value is null 
		   if(!monthlySIPEditText.getText().toString().matches("")){
			   monthlySIP				=Double.parseDouble(monthlySIPEditText.getText().toString().replace(",",""));;
			}
			
		  //if the value is null than assign it to zero
		   else if(monthlySIPEditText.getText().toString().matches("")){
			   monthlySIP				=0;
		   }
		   
			
		    //Get text from periodEditText handle by getText() change it to string by toString()
		    // parse it to double and save in period
		    //matches("") is for checking whether the value is null
			if(!periodEditText.getText().toString().matches("")){
				period						=Double.parseDouble(periodEditText.getText().toString());;
				}
			
			//if the value is null than assign it to zero
			else if(periodEditText.getText().toString().matches("")){
				period						=0;
			}
			   
			    
			    
			//Get text from annualInterestRateEditText handle by getText() change it to string by toString()
			// parse it to double and save in annualInterestRate
			//matches("") is for checking whether the value is null   						
			if(!annualInterestRateEditText.getText().toString().matches("")){
				annualInterestRate			=Double.parseDouble(annualInterestRateEditText.getText().toString());;
				}
			
			else if(annualInterestRateEditText.getText().toString().matches("")){
				annualInterestRate			=0;
			}
			
			    annualInterestRate			=annualInterestRate/100; // as annualInterstRate is in % divide by 100.
	
			    
		   //Get text from monthlyDepositAnnualRateEditText handle by getText() change it to string by toString()
		   // parse it to double and save in monthlyDepositAnnualRate
		   //matches("") is for checking whether the value is null   						
			if(!monthlyDepositAnnualRateEditText.getText().toString().matches("")){
				monthlyDepositAnnualRate			=Double.parseDouble(monthlyDepositAnnualRateEditText.getText().toString());;
					}
				
			else if(monthlyDepositAnnualRateEditText.getText().toString().matches("")){
				monthlyDepositAnnualRate			=0;
				}
				
			monthlyDepositAnnualRate			=monthlyDepositAnnualRate/100; // as monthlyDepositAnnualRate is in % divide by 100.    
			    
	}
	
	
	
	
	/*
	 * onButtonClick() contains methods which will be called when 
	 * a reset or calculate button is called
	 * If reset button is called it will clear all editText values
	 * If calculate button is called it will calculate the compound interest and store it in coumpoundInterest
	 */
	
	public void onButtonClick(){
		
		//If calculate button is clicked
		
		calculateButton.setOnClickListener(new View.OnClickListener() {
			
			
			
			//called when Calculate button is clicked
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				//to remove the software keyboard
				InputMethodManager imm = (InputMethodManager)getSystemService(
					      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(annualInterestRateEditText.getWindowToken(), 0);
				 
				
		        getValuesFromReferences();	
		        
		        calculatePrincipalInvested();
		        calculateMaturityValue();
		        calculateInterestEarned();
		       
				
		      
		
				// fillValueHere.setText("The value is " + amount);
				 displayResult();
				
			}
		});
		
		
		//if reset button is clicked
		
		resetButton.setOnClickListener(new View.OnClickListener() {
			
			//called when reset button is clicked
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//to remove the software keyboard
				InputMethodManager imm = (InputMethodManager)getSystemService(
					      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(annualInterestRateEditText.getWindowToken(), 0);
				//this will set principal Amount Edit text to null
				initialInvestmentEditText.setText(null);
				
				//this will set monthly Deposit Edit Text to null
				monthlySIPEditText.setText(null);
				
				//this will set monthly Deposit Annual Rate Edit Text to null
				monthlyDepositAnnualRateEditText.setText(null);
				
				//this will set period Edit Text to null
				periodEditText.setText(null);
				
				
				//this will set annual Interest rate edit text to null
				annualInterestRateEditText.setText(null);
				
				
				//this will set spinner value to monthly(default)
				compoundingSpinner.setSelection(0);
				
				
				//this will set principal Invested to 0
				principalInvestedTextViewDisplay.setText("0");
				
				
				//this will set interest Earned to 0
				interestEarnedTextViewDisplay.setText("0");
				
				//this will set maturity Value to 0
				maturityValueTextViewDisplay.setText("0");
				
				
			}
		});
	}
	
	
	
	/*
	 * displayAmount() is for displaying the interest 
	 * after calculate interest is called
	 */
	;
	public void displayResult(){
		
		DecimalFormat df = new DecimalFormat("#,###.##");
		
		
		//to display principalInvested
		principalInvestedTextViewDisplay.setText(""+df.format(principalInvested));
		
		//to display interestEarned		
		interestEarnedTextViewDisplay.setText(""+df.format(interestEarned));
		
		//to display maturityValue
		maturityValueTextViewDisplay.setText(""+df.format(maturityValue));
		
				
	}
	
    
	
	/*
	 * compoundingSpinnerImplementation() is for implementing a listener whenever a value is selected from spinner
	 * and assign the value to compounding i.e for
	 * monthly		----->compounding(n)=12
	 * yearly		----->compounding(n)=1
	 */
	
	public void compoundingSpinnerImplementation(){		
				// Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
							R.array.compound_array, android.R.layout.simple_spinner_item);
				// Specify the layout to use when the list of choices appears
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner
				compoundingSpinner.setAdapter(adapter);
		
				compoundingSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			 
			 
				//this will be called whenever the spinner is selected
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
	                    int position, long id) {
				//get the value from spinner into compoundingString and convert to string
				compoundingString 		=compoundingSpinner.getSelectedItem().toString();
	    			
	    			
				//Set the value of compounding(n)  according to
				//Daily				n=356
			    //Weekly			n=52
			    //Monthly			n=12
			    //Quaterly			n=4
			    //Semianually		n=2
			    //Anually        	n=1
			    //No compounding	n=0
			    //We will be using only Monthly and yearly	
	    			
	    			
			//   if(compoundingString.equals("Daily")){
			//    		compounding		=356;
			//    }
			//   if(compoundingString.equals("Weekly")){
			//    		compounding		=52;
			//    }
			     if(compoundingString.equals("Monthly")){
			    		compounding		=12;
			      }
			//    if(compoundingString.equals("Quaterly")){
			//    		compounding		=4;
			//    }
			//    if(compoundingString.equals("Semianually")){
			//    		compounding		=2;
			//    }		
			      if(compoundingString.equals("Annually")){
			    		compounding		=1;
			      }		
			//    if(compoundingString.equals("No Compound")){
			//    		compounding		=0;
			//    }
	    							
				  }
	 
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
	 
					}
				});
	
	}
	
	

	
	/*
	 * calculate MauturityValue() is for calculating 
	 * Maturity value
	 */
	
	
	public void calculateMaturityValue(){
		int i;
	    double tempMaturityValue;      //tempMaturityValue is the future amount after a year
		//if compounding = monthly (n=12)
		//now as there is annual interest rate for monthly SIP,
		//monthly SIP will increment every year
		//monthlySIP					= monthlySIP + monthlySIP*monthlyDepositAnnualRate;
		//=>monthlySIP                	= monthlySIP(1+monthlyDepositAnnualRate);
		//monthlySIP for n year 		= monthlySIP(1+monthlyDepositAnnualRate)^n
	    //maturity value after first year K1(tempMaturityValue) 	= P*(1+r/n)^n*1 + M*(((1+r/n)^n*1-1)/((1+r/n)-1))
	    //maturity value after second year K2   					= K1*(1+r/n)^n*1 + M*(1+monthlyDepositAnnualRate)^1*(((1+r/n)^n*1-1)/((1+r/n)-1))
		//we can calculate the maturity value for subsequent years in the following way :)
		if(compounding==12){
			maturityValue=0;
			tempMaturityValue 	= initialInvestment;
			for(i=0;i<period;i++){
			tempMaturityValue	=  tempMaturityValue*(Math.pow((1+(annualInterestRate/compounding)),compounding))+
					(monthlySIP* Math.pow((1+monthlyDepositAnnualRate),i))*(( (Math.pow((1+(annualInterestRate/compounding)),compounding))-1)/(annualInterestRate/compounding));
			}
			maturityValue	= tempMaturityValue;
		}
		
        
        
        	//if compounding = yearly (n=1)
			//now as there is annual interest rate for monthly SIP,
			//monthly SIP will increment every year
			//monthlySIP					= monthlySIP + monthlySIP*monthlyDepositAnnualRate;
			//=>monthlySIP                	= monthlySIP(1+monthlyDepositAnnualRate);
			//monthlySIP for n year 		= monthlySIP(1+monthlyDepositAnnualRate)^n
			//maturity value after first year K1(tempMaturityValue) 	= P*(1+r/n)^nt + M*(((1+r/n)^nt-1)/((1+r/n)-1))
			//maturity value after second year K2   					= K1*(1+r/n)^nt + M*(1+monthlyDepositAnnualRate)^1*(((1+r/n)^nt-1)/((1+r/n)-1))
			//we can calculate the maturity value for subsequent years in the following way :)
		
		if(compounding==1){
			maturityValue=0;
			tempMaturityValue 	= initialInvestment;
			for(i=0;i<period;i++){
				Y			=12*monthlySIP*Math.pow(((1+monthlyDepositAnnualRate)),i) + 6.5*monthlySIP*Math.pow(((1+monthlyDepositAnnualRate)),i)*annualInterestRate;	
				tempMaturityValue = tempMaturityValue*(Math.pow((1+(annualInterestRate/compounding)),compounding)) +
      				           Y*(( (Math.pow((1+(annualInterestRate/compounding)),compounding))-1)/(annualInterestRate/compounding));
			     }
			maturityValue	= tempMaturityValue;
			
			}
		
        
        
        //rounding off to 2 decimal places
        maturityValue			=(Math.round(maturityValue*100));
        maturityValue			=maturityValue/100;
        
        if(compounding==0){
        	maturityValue		= initialInvestment*(1+annualInterestRate*period);
        }
		
		
	}
	
	
	/*
	 * 
	 * principalInvested() is for calculating 
	 * total amount of money put by the invester
	 * Principal Invested= Initial Investment + Monthly Deposit*12*Period(years)
	 */
	
	
	public void calculatePrincipalInvested(){

		int i;
		principalInvested= 0;
		for(i=0;i<period;i++){
			//monthlySIP will be incrementing every year
			principalInvested = principalInvested + 12*monthlySIP*Math.pow((1+monthlyDepositAnnualRate),i);
			
		}
		principalInvested = principalInvested + initialInvestment;
	//	principalInvested	=initialInvestment + monthlySIP*12*period;
	}
		
		
	
	/*
	 * interestEarend() is for calculating the interest in rupees got 
	 * by the invester
	 * Interest Earned	= Maturity Value - Principal Invested
	 */
	
	
	public void calculateInterestEarned(){
		
		interestEarned	= maturityValue - principalInvested;
		
	}
		
		
	
	
		
	
	/*
	
	//Get values from EditText fields into the variables by referring their ID's
		
		principalAmountEditText				=(EditText) findViewById(R.id.principalAmountEditText);
		monthlySIPEditText				=(EditText) findViewById(R.id.monthlySIPEditText);
		periodEditText						=(EditText) findViewById(R.id.periodEditText);
		annualInterestRateEditText			=(EditText) findViewById(R.id.annualInterestRateEditText); 
	//	compoundingEditText					=(EditText) findViewById(R.id.compoundingEditText);
		
		
		
		//Get values from spinner fields by referring their ID's
		
		compoundingSpinner					=(Spinner) findViewById(R.id.compoundingSpinner);
		
		
		
		
*/		
		//Get values from the TextView fields into the variables by referring their ID's
		
/*		principalAmountTextView				=(TextView) findViewById(R.id.principalAmountTextView);
		monthlySIPTextView				=(TextView) findViewById(R.id.monthlySIPTextView);
		periodTextView						=(TextView) findViewById(R.id.periodTextView);
		anuualInterestRateTextView			=(TextView) findViewById(R.id.annualInterestRateTextView);
		compoundingTextView					=(TextView) findViewById(R.id.compoundingTextView);
*/		
		
		
		//Get values from the TextView fields into the variables by referring their ID's
		
/*		fillValueHere						=(TextView) findViewById(R.id.fillValueHere);
		
		
		
		//Get values from Button fields into the variables by referring their ID's
		
		resetButton							=(Button) findViewById(R.id.resetButton);
		calculateButton						=(Button) findViewById(R.id.calculateButton);
		
		
		
		//setting the button listeners for resetButton
		
		calculateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				
				//convert principalAmount to Double from string
											
				if(!principalAmountEditText.getText().toString().matches("")){
				principalAmount			=Double.parseDouble(principalAmountEditText.getText().toString());;
				}
				
				
				if(!periodEditText.getText().toString().matches("")){
					period			=Double.parseDouble(periodEditText.getText().toString());;
					}
				
				period		=period/12;
				
				
				if(!annualInterestRateEditText.getText().toString().matches("")){
					annualInterestRate			=Double.parseDouble(annualInterestRateEditText.getText().toString());;
					}
				
				annualInterestRate		=annualInterestRate/100;	
				
				
				
				double amount = principalAmount * Math.pow(1 + (annualInterestRate / compounding), compounding * period);
			
			//	double interest = amount - principalAmount;
				
			//	compoundingString 		=compoundingSpinner.getSelectedItem().toString();
				fillValueHere.setText("The value is " + amount);
				
				
			}
		});
		
	
		compoundingSpinner.setOnItemSelectedListener(this);
		
		
		
		
		
	}
*/

//	public void compoundingSpinnerImplementation(){
		
//	}
	
	/*calculateMaturityValueForEachYear(double year, double compounding)
	 * is for calculating maturity value for each year with the updated monthly SIP
	 * updated monthly SIP means interest rate added to the monthly SIP 
	 * 
	 */
	
//	public float calculateMaturityValueForEachYear(double year, double compounding){
		
		
//		if(compounding==12){
			
//		}
		
		
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
