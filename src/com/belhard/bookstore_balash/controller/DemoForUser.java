package com.belhard.bookstore_balash.controller;

import com.belhard.bookstore_balash.service.UserService;
import com.belhard.bookstore_balash.service.dto.UserDto;
import com.belhard.bookstore_balash.service.impl.UserServiceImpl;

import java.util.Scanner;

public class DemoForUser {
    public static void main(String[] args) {
        Scanner stroka = new Scanner(System.in);
        int action;

        System.out.println("1 - find user by id");
        System.out.println("2 - find all users");
        System.out.println("3 - find user by email");
        System.out.println("4 - find user by lastname");
        System.out.println("5 - find user by login");
        System.out.println("6 - delete user");
        System.out.println("7 - update user");
        System.out.println("8 - create user");
        System.out.println("9 - count user");
        System.out.println("10 - exit");

        do {
            System.out.println();
            System.out.print("Make o choose: ");
            action = stroka.nextInt();
            stroka.nextLine();

            UserService userService = new UserServiceImpl();

            switch (action) {
                case 1: {
                    System.out.println("getById");
                    if (userService.getById(5).getId() == null) {
                        System.out.println("The user was not found");
                    }
                    break;
                }

                case 2: {
                    System.out.println("getALl");
                    if (userService.getAll().isEmpty()) {
                        System.out.println("The user was not found");
                    }
                    break;
                }

                case 3: {
                    System.out.println("getByEmail");
                    if (userService.getByEmail("email12@mail.ru") == null) {
                        System.out.println("The user was not found");
                    }
                    break;
                }

                case 4: {
                    System.out.println("getByLastName");
                    if (userService.getByLastName("Last23").isEmpty()) {
                        System.out.println("The user was not found");
                    }
                    break;
                }

                case 5: {
                    System.out.println("getByLogin");
                    if (userService.getByLogin("user16") == null) {
                        System.out.println("The user was not found");
                    }
                    break;
                }

                case 6: {
                    System.out.println("delete");
                    if (userService.delete(100)) {
                        System.out.println("The user has been deleted");
                    } else {
                        System.out.println("The book with this id was not deleted because it was not found");
                    }
                    break;
                }

                case 7: {
                    System.out.println("update");
                    UserDto book1 = new UserDto(7L, "user78", "111-0-124456-47-3", "First9", "Last9", "email78@mail.ru", 3L);
                    if (userService.update(book1).getId() == null) {
                        System.out.println("The user has not been updated");
                    }
                    break;
                }

                case 8: {
                    System.out.println("create");
                    UserDto book2 = new UserDto(null, "user77", "111-0-124456-47-3", "First9", "Last9", "email77@mail.ru", 3L);
                    userService.create(book2);
                    break;
                }

                case 9: {
                    System.out.println("getcountAll");
                    System.out.println(userService.getcountAll());
                    break;
                }

                case 10: {
                    System.out.print("Completing the program");
                    break;
                }

                default: {
                    System.out.print("There is no such command");
                }
            }
        } while (action != 10);
    }
}
