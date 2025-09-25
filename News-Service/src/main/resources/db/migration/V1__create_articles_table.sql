CREATE TABLE news_article(
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    author VARCHAR(255),
    category VARCHAR(255),
    read_time INT,
    image_url VARCHAR(255),
    creation_date TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE
);

--Insert initial data
INSERT INTO news_article (id, title, content, author, category, read_time, image_url, creation_date, last_modified_date)
VALUES (1, 'The Rise of Microservices', 'Microservices architecture is a hot topic...', 'Jane Doe', 'Technology', 10, 'https://placehold.co/600x400/000000/FFFFFF?text=Microservices', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO news_article (id, title, content, author, category, read_time, image_url, creation_date, last_modified_date)
VALUES (2, 'The Future of AI', 'Artificial intelligence is changing the world...', 'John Smith', 'Technology', 15, 'https://placehold.co/600x400/000000/FFFFFF?text=AI', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO news_article (id, title, content, author, category, read_time, image_url, creation_date, last_modified_date)
VALUES (3, 'A Guide to Cloud Computing', 'Cloud computing offers flexibility...', 'Emily White', 'Technology', 20, 'https://placehold.co/600x400/000000/FFFFFF?text=Cloud+Computing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
