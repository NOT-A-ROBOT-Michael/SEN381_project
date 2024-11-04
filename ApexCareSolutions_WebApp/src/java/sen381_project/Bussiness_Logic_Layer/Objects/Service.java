
package sen381_project.Bussiness_Logic_Layer.Objects;

public class Service {
    Integer service_ID, csa_ID, contract_ID, survey_ID, client_ID, address_ID, note_ID;
    String description, status, priority, requested_Date, completed_Date, service_Title;
    
    public Service()
    {
    }
    
    public Service(Integer service_ID, Integer contract_ID)
    {
        this.service_ID = service_ID;
        this.contract_ID = contract_ID;
    }
    
    public Integer getContractID()
    {
        return this.contract_ID;
    }
}
