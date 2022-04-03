package com.example.bettingproject.shared.constants;

import com.example.bettingproject.shared.exceptions.NonInstantiableException;

public class BettingConstants {

    public static final String MATCH_ENTRY_HAS_SUBMITTED_SUCCESSFULLY = "Match entry has submitted successfully";
    public static final String MATCH_ENTRY_HAS_UPDATED_SUCCESSFULLY = "Match entry has updated successfully";
    public static final String MATCH_ENTRY_HAS_DELETED_SUCCESSFULLY = "Match entry has deleted successfully";
    public static final String ODD_ENTRY_HAS_UPDATED_SUCCESSFULLY = "Odd entry has updated successfully";
    public static final String ERROR_AT_SAVE_MATCH_FOR = "Error at saveMatch for: {}";
    public static final String ERROR_AT_ADD_MATCH_FOR_ENTITY = "Error at addMatch for entity: {}";
    public static final String ERROR_AT_DELETE_MATCH_FOR_ENTITY = "Error at deleteMatch for entity: {}";
    public static final String ERROR_AT_SAVE_ODDS_FOR_ENTITY = "Error at saveOdds for entity: {}";
    public static final String MATCH_ENTRY_COULD_NOT_BE_SUBMITTED = "Match Entry could not be Submitted";
    public static final String ODD_ENTRY_COULD_NOT_BE_SUBMITTED = "Odd Entry could not be Submitted";
    public static final String MATCH_ENTRY_COULD_NOT_BE_UPDATED = "Match Entry could not be Updated";
    public static final String MATCH_ENTRY_COULD_NOT_BE_DELETED= "Match Entry could not be Deleted";
    public static final String MATCH_ENTRY_COULD_NOT_BE_FOUND = "Match Entry could not be Found";
    public static final String ODDS_ALREADY_EXIST = "Odds already exist";


    private BettingConstants() throws NonInstantiableException {
        throw new NonInstantiableException("Betting Constants class cannot be instantiated");
    }

}
