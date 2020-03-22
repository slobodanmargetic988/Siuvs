/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.pbosko.siuvs.shared;
import com.twilio.Twilio;
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
/**
 *
 * @author slobodan
 */
public class SMSGenerator {
    public static final String ACCOUNT_SID = "AC9fbc18781cd0ef9a4edf08bc4ddb9da6"; 
    public static final String AUTH_TOKEN = "ca7c046dc4cd5de3a7f2301d8c8a09d8";
    
    public String SendSMS(String text, String recipient){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(  
                new com.twilio.type.PhoneNumber(recipient), 
                new com.twilio.type.PhoneNumber("SIUVS"), text)
                    .create();
    
    return"";
    }
    
}
