/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;

import businesslogiclayer.object.AR_Notes;
import datalayer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class NoteLogic {
    ConnectionProvider cpt = new ConnectionProvider();
    
    public AR_Notes CreateNoteObject(Integer serviceID, String note)
    {
        AR_Notes notes = new AR_Notes();
        
        notes.setNoteID(serviceID).setNoteText(note);
        
        return notes;
    }
    
    public void SaveNote(Integer serviceID, String note){
        
        try
        {
            //Initialize
            AR_Notes notes;

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
    
    
    public AR_Notes getNote(Integer noteID){
        
        try
        {
            //Initailize
            AR_Notes note;

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
