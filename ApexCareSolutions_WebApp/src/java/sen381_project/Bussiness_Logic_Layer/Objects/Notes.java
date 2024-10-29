/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author arlow
 */
public class Notes {
    private Integer NoteID;
    private String noteText;
    
    public Notes(){}
    
    public Notes (Integer serviceID, String noteText)
    {
        this.NoteID = serviceID;
        this.noteText = noteText;
    }
    
    public String getNoteText(){
        return this.noteText;
    }
    
    public Integer getNoteID(){
        return this.NoteID;
    }
    
    public Notes setNoteText(String noteText){
        this.noteText = noteText;
        return this;
    }
    
    public Notes setNoteID(Integer NoteID){
        this.NoteID = NoteID;
        return this;
    }
    
}
