package za.ac.cput.school_management_grp33.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;

@Repository
public interface StudentAddressRepository extends JpaRepository<StudentAddress, String> {
}
