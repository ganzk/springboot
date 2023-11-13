package com.controller;

import com.domain.Guest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping
public class GuestController {

    @PostMapping("/guest")
    public String add(@Valid Guest guest, BindingResult result){
        if(result.getErrorCount() > 0){
            StringBuffer stringBuffer = new StringBuffer();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (int i = 0; i < fieldErrors.size(); i++) {
                stringBuffer.append(fieldErrors.get(i).getField());
                stringBuffer.append("/t");
                stringBuffer.append(fieldErrors.get(i).getDefaultMessage());
                stringBuffer.append("/n");
            }
            return stringBuffer.toString();
        }
        return "Success";
    }


}
