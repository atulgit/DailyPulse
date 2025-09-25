--This script adds the news publication date to news-article table of the database
ALTER TABLE news_article
ADD COLUMN publication_date TIMESTAMP WITH TIME ZONE

-- It's a good practice to set a default value for existing rows.
-- This prevents the column from being Null for articles.
UPDATE news_article
SET publication_date = NOW()
WHERE publication_date IS NULL;



