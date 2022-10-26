package tw.com.ispan.eeit48.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.EmpRepository;
import tw.com.ispan.eeit48.domain.EmpBean;

@Service
@Transactional
public class EmpService {
    @Autowired
    private EmpRepository empRepository;

    @Transactional(readOnly = true)
    public EmpBean login(Integer empid, String passwd) {

        EmpBean bean = empRepository.login(empid);
        if (bean != null) {
            if (passwd != null && passwd.length() != 0) {
                String password = bean.getPasswd();
                if (passwd.equals(password)) {
                    return bean;
                }
            }
        }
        return null;
    }

    public void updateResetPasswordToken(String token, String email) throws EmpBeanNotFoundException {
        EmpBean empBean = empRepository.findByEmail(email);

        if (empBean != null) {
            empBean.setResetPasswordToken(token);
            empRepository.save(empBean);
        } else {
            throw new EmpBeanNotFoundException("驗證信箱與原註冊信箱不符" + email);
        }
    }

    public EmpBean get(String resetPasswordToken) {
        return empRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(EmpBean empBean, String newPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(newPassword);
        
        empBean.setPasswd(newPassword);
        empBean.setResetPasswordToken(null);
        
        empRepository.save(empBean);
    }
}
