/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Bussiness_Logic_Layer.Objects.Notes;
import sen381_project.Data_Layer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class NotesThing {
    ConnectionProvider cpt = new ConnectionProvider();
    
    public Notes CreateNoteObject(Integer serviceID, String note)
    {
        Notes notes = new Notes();
        
        notes.setNoteID(serviceID).setNoteText(note);
        
        return notes;
    }
    
    public void SaveNote(Integer serviceID, String note){
        
        try
        {
            //Initialize
            Notes notes;

            // create notes object
            notes = CreateNoteObject(serviceID, note);

            //test if note already exists
            //if exist then update else insertNote
            cpt.SaveNotes(notes);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to save note information: " + e.getMessage());
        }
        
        
        
    }
    
    
    public Notes getNote(Integer noteID){
        
        try
        {
            //Initailize
            Notes note;

            //run get sql
            note = cpt.getNotes(noteID);


            //create object


            //return note
            return note; 
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to get note information: " + e.getMessage());
        }
        
        return null;
    }
}
