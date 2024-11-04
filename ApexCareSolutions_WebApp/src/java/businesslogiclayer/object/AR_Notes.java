/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.object;

/**
 *
 * @author arlow
 */
public class AR_Notes {
    private Integer NoteID;
    private String noteText;
    
    public AR_Notes(){}
    
    public AR_Notes (Integer noteID, String noteText)
    {
        this.NoteID = noteID;
        this.noteText = noteText;
    }
    
    public String getNoteText(){
        return this.noteText;
    }
    
    public Integer getNoteID(){
        return this.NoteID;
    }
    
    public AR_Notes setNoteText(String noteText){
        this.noteText = noteText;
        return this;
    }
    
    public AR_Notes setNoteID(Integer NoteID){
        this.NoteID = NoteID;
        return this;
    }
    
}
