package com.example.notAnotherResume.util;

import org.jsoup.Jsoup;

public class HtmlUtil {

	 public static String getHtmlTemplate() {
	        return """
	                <!DOCTYPE html>
	                <html>
	                <head>
	                    <style>
	                        body {
	                            font-family: Arial, sans-serif;
	                            margin: 20px;
	                            line-height: 1.6;
	                        }
	                        .header {
	                            text-align: center;
	                            margin-bottom: 30px;
	                        }
	                        .header h1 {
	                            margin: 0;
	                        }
	                        .section {
	                            margin-bottom: 20px;
	                        }
	                        .section h2 {
	                            background-color: #f4f4f4;
	                            padding: 10px;
	                            border-left: 4px solid #007BFF;
	                        }
	                        .section p {
	                            margin: 0;
	                        }
	                        .contact-info {
	                            font-size: 0.9em;
	                            margin-top: 10px;
	                        }
	                    </style>
	                </head>
	                <body>
	                    <div class="header">
	                        <h1>{{fullName}}</h1>
	                        <p class="contact-info">Email: {{email}} | Phone: {{phone}} | Address: {{address}}</p>
	                        <p class="contact-info">Websites: {{websites}}</p>
	                    </div>
	                    <div class="section">
	                        <h2>Summary</h2>
	                        <p>{{summary}}</p>
	                    </div>
	                    <div class="section">
	                        <h2>Competencies</h2>
	                        <p>{{competencies}}</p>
	                    </div>
	                    <div class="section">
	                        <h2>Experience</h2>
	                        <p>{{experience}}</p>
	                    </div>
	                    <div class="section">
	                        <h2>Achievements</h2>
	                        <p>{{achievements}}</p>
	                    </div>
	                    <div class="section">
	                        <h2>Hobbies</h2>
	                        <p>{{hobbies}}</p>
	                    </div>
	                    <div class="section">
	                        <h2>Fluency</h2>
	                        <p>{{fluency}}</p>
	                    </div>
	                    <div class="section">
	                        <h2>Disclaimer</h2>
	                        <p>{{disclaimer}}</p>
	                    </div>
	                </body>
	                </html>
	                """;
	    }
}

