package org.example.behavioural.COR;

public class TeamLead extends LeaveApprover{
    public void approveLeave(int days){
        if(days<=2){
            System.out.println("TeamLead approved "+ days+" days leave.");
        }else if (nextApprover !=null){
            nextApprover.approveLeave(days);
        }
    }
}
