package org.example.behavioural.COR;

public class CorClient {
    public static void main(String[] args) {
        LeaveApprover teamLead =  new TeamLead();
        LeaveApprover manager = new Manager();
        LeaveApprover director = new Director();

        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);

        teamLead.approveLeave(4);
    }
}
