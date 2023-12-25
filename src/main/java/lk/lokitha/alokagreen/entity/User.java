package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String userName;
    private String password;
    private String employeeId;
}
