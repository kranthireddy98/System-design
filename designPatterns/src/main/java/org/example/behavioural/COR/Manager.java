package org.example.behavioural.COR;

public class Manager extends LeaveApprover{

    @Override
    public void approveLeave(int days) {
        if(days<=4){
            System.out.println("Manager approved "+ days+" days leave.");
        }else if(nextApprover!=null){
            nextApprover.approveLeave(days);
        }
    }
}
