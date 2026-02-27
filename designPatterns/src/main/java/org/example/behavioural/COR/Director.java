package org.example.behavioural.COR;

public class Director extends LeaveApprover{

    @Override
    public void approveLeave(int days) {
        System.out.println("Director approved "+ days+" days leave.");

    }
}
