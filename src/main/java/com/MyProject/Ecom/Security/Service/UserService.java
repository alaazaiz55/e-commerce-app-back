package com.MyProject.Ecom.Security.Service;

import com.MyProject.Ecom.entity.ChangePasswordRequest;
import com.MyProject.Ecom.entity.User;
import com.MyProject.Ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.MyProject.Ecom.entity.UserRole.ADMIN;
import static com.MyProject.Ecom.entity.UserRole.CUSTOMER;


@Service
@RequiredArgsConstructor
public class UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    public User getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public List<User> getAll() {

        return userRepository.findByRole(ADMIN);

    }
    public List<User> getAllGrh() {

        return userRepository.findByRole(CUSTOMER);
    }
   /* public boolean deleteEmpolyee(int id) {
        try {
            userRepository.deleteById(id);
            return true; // Return true if the document is successfully deleted
        } catch (Exception e) {
            // Log or handle the exception if needed
            return false; // Return false if there's an error deleting the document
        }
    }

    @Transactional
    public String archiverUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus("archived"); // Mark the user as archived
            userRepository.saveAndFlush(user); // Save the updated user back to the database
            return "User archived successfully.";
        } else {
            return "User not found.";
        }
    }*/
}
