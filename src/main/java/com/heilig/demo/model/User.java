package com.heilig.demo.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * This class represents a user inside the system. An instance of a user can be persisted in DB.
 *
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
@Data
@ToString
@Entity
@Table
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userseq")
  @SequenceGenerator(name = "userseq", allocationSize = 1)
  private Long id;
  private String lastname;
  private String firstname;
  private LocalDate birthDate;

}
