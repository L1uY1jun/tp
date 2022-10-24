package seedu.trackascholar.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.trackascholar.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalApplicants.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.commons.exceptions.IllegalValueException;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;

public class JsonAdaptedApplicantTest {

    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_SCHOLARSHIP = " ";
    private static final String INVALID_APPLICATION_STATUS = "failure";
    private static final String INVALID_MAJOR = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_SCHOLARSHIP = BENSON.getScholarship().toString();
    private static final String VALID_APPLICATION_STATUS = BENSON.getApplicationStatus().toString();
    private static final List<JsonAdaptedMajor> VALID_MAJORS = BENSON.getMajors().stream()
            .map(JsonAdaptedMajor::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(BENSON);
        assertEquals(BENSON, applicant.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(INVALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(null,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                INVALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                null, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, INVALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, null, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidScholarship_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, INVALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = Scholarship.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullScholarship_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Scholarship.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, INVALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = ApplicationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_APPLICATION_STATUS, VALID_MAJORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Scholarship.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidMajors_throwsIllegalValueException() {
        List<JsonAdaptedMajor> invalidMajors = new ArrayList<>(VALID_MAJORS);
        invalidMajors.add(new JsonAdaptedMajor(INVALID_MAJOR));
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, invalidMajors);
        assertThrows(IllegalValueException.class, applicant::toModelType);
    }

}