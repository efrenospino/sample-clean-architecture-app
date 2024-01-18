package com.spotapp.mobile.ui.feature.home

import com.spotapp.mobile.data.models.Option
import com.spotapp.mobile.data.models.Question

fun generateQuestions(): List<Question> {
    return listOf(
        Question(
            "What is the purpose of SQL in database management?",
            listOf(
                Option(1, "Secure file storage", false),
                Option(2, "Structured Query Language", true),
                Option(3, "Simple Text Language", false),
                Option(4, "Software Quality Level", false)
            ),
            Option(2, "Structured Query Language", true)
        ),
        Question(
            "Which of the following is a widely used version control platform for open-source projects?",
            listOf(
                Option(1, "SVN", false),
                Option(2, "Git", true),
                Option(3, "Mercurial", false),
                Option(4, "Perforce", false)
            ),
            Option(2, "Git", true)
        ),
        Question(
            "What is the main purpose of an operating system?",
            listOf(
                Option(1, "Manage computer hardware", true),
                Option(2, "Create graphic designs", false),
                Option(3, "Execute web requests", false),
                Option(4, "Generate code documentation", false)
            ),
            Option(1, "Manage computer hardware", true)
        ),
        Question(
            "Which programming language is commonly used for developing Android applications?",
            listOf(
                Option(1, "Swift", false),
                Option(2, "Kotlin", true),
                Option(3, "Objective-C", false),
                Option(4, "Java", false)
            ),
            Option(2, "Kotlin", true)
        ),
        Question(
            "What does the term 'IoT' stand for?",
            listOf(
                Option(1, "Internet of Technology", false),
                Option(2, "Internet of Things", true),
                Option(3, "Input/Output Testing", false),
                Option(4, "Innovative Object Technologies", false)
            ),
            Option(2, "Internet of Things", true)
        ),
        Question(
            "Which protocol is used for sending and receiving email messages?",
            listOf(
                Option(1, "HTTP", false),
                Option(2, "SMTP", true),
                Option(3, "FTP", false),
                Option(4, "POP3", false)
            ),
            Option(2, "SMTP", true)
        ),
        Question(
            "What is the purpose of a CDN (Content Delivery Network) in web development?",
            listOf(
                Option(1, "Database management", false),
                Option(2, "Website performance optimization", true),
                Option(3, "User authentication", false),
                Option(4, "Code version control", false)
            ),
            Option(2, "Website performance optimization", true)
        ),
        Question(
            "Which of the following is a commonly used relational database management system (RDBMS)?",
            listOf(
                Option(1, "MongoDB", false),
                Option(2, "MySQL", true),
                Option(3, "Redis", false),
                Option(4, "Cassandra", false)
            ),
            Option(2, "MySQL", true)
        ),
        Question(
            "What is the purpose of a CSS (Cascading Style Sheets) in web development?",
            listOf(
                Option(1, "Server-side scripting", false),
                Option(2, "Database management", false),
                Option(3, "User authentication", false),
                Option(4, "Styling and formatting web content", true)
            ),
            Option(4, "Styling and formatting web content", true)
        ),
        Question(
            "Which of the following is a common cloud computing service provider?",
            listOf(
                Option(1, "Adobe", false),
                Option(2, "Microsoft Azure", true),
                Option(3, "Salesforce", false),
                Option(4, "Oracle", false)
            ),
            Option(2, "Microsoft Azure", true)
        )
    )
}