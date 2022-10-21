package seedu.trackascholar.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalApplicants.ALICE;
import static seedu.trackascholar.testutil.TypicalApplicants.BENSON;
import static seedu.trackascholar.testutil.TypicalApplicants.BOB;
import static seedu.trackascholar.testutil.TypicalApplicants.CARL;
import static seedu.trackascholar.testutil.TypicalApplicants.DANIEL;
import static seedu.trackascholar.testutil.TypicalApplicants.ELLE;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.model.tag.Tag;
import seedu.trackascholar.testutil.ApplicantBuilder;

import java.util.Set;

public class ApplicantTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Applicant applicant = new ApplicantBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> applicant.getTags().remove(0));
    }

    @Test
    public void isSameApplicant() {
        // same object -> returns true
        assertTrue(ALICE.isSameApplicant(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplicant(null));

        // same name, all other attributes different -> returns true
        Applicant editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withScholarship(VALID_SCHOLARSHIP_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameApplicant(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameApplicant(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Applicant editedBob = new ApplicantBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameApplicant(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ApplicantBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameApplicant(editedBob));
    }

    @Test
    public void sortByName() {
        // Comparing names based on lexicographical order
        assertTrue(Applicant.sortByName().compare(ALICE, BENSON) == -1);
        assertTrue(Applicant.sortByName().compare(CARL, BENSON) == 1);
    }

    @Test
    public void sortByScholarship() {
        // returns 4 which is the result of comparing Alice and Elle's names
        // both Alice and Elle have the same Scholarships and thus names are used as a tiebreaker
        assertTrue(Applicant.sortByScholarship().compare(ELLE, ALICE) == 4);

        // returns 18 which is the result of comparing Arts and Sports Scholarships
        assertTrue(Applicant.sortByScholarship().compare(CARL, DANIEL) == 18);
    }


    @Test
    public void sortByStatus_acceptedAndRejected_returnsNegativeOne() {
        // returns -1 from comparing accepted and rejected
        assertTrue(Applicant.sortByStatus().compare(BENSON, DANIEL) == -1);

        // returns -1 from comparing pending and rejected
        assertTrue(Applicant.sortByStatus().compare(ALICE, DANIEL) == -1);

        // both Alice and Elle have the same status and thus names are used as a tiebreaker
        assertTrue(Applicant.sortByStatus().compare(ALICE, CARL) == -2);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Applicant aliceCopy = new ApplicantBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different applicant -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Applicant editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withScholarship(VALID_SCHOLARSHIP_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringTest() {
        String expectedString = "Benson Meier; Phone: 98765432; Email: johnd@example.com; " +
                "Scholarship: Merit; Application Status: accepted; Tags: [owesMoney][friends]";
        assertTrue(BENSON.toString().equals(expectedString));

    }

}
