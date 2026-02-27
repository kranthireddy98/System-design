package org.example.behavioural.COR;

public abstract class LeaveApprover {
    protected LeaveApprover nextApprover;

    public void setNextApprover(LeaveApprover nextApprover){
        this.nextApprover=nextApprover;
    }

    public abstract void approveLeave(int days);
}
