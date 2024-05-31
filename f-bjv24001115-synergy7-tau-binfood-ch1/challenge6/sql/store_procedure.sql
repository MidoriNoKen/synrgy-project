-- Create stored procedure for get all users
CREATE OR REPLACE FUNCTION all_users()
RETURNS TABLE (
    id BIGINT,
    username VARCHAR,
    email VARCHAR,
    password VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT u.id, u.username, u.email, u.password
    FROM Users u;
END;
$$ LANGUAGE plpgsql;

-- Create stored procedure for get a user by ID
CREATE OR REPLACE FUNCTION show_user(
    p_id BIGINT
) RETURNS TABLE (
    id BIGINT,
    username VARCHAR,
    email VARCHAR,
    password VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT u.id, u.username, u.email, u.password
    FROM Users u
    WHERE u.id = p_id;
END;
$$ LANGUAGE plpgsql;

-- Create stored procedure for inserting a new user
CREATE OR REPLACE FUNCTION create_user(
    p_username VARCHAR,
    p_email VARCHAR,
    p_password VARCHAR
) RETURNS VARCHAR AS $$
DECLARE
    result_message VARCHAR;
BEGIN
    BEGIN
        INSERT INTO Users (username, email, password)
        VALUES (p_username, p_email, p_password);
        result_message := 'User created successfully.';
        RAISE NOTICE 'User % created successfully.', p_username;
    EXCEPTION WHEN others THEN
        result_message := 'Failed to create user: ' || SQLERRM;
        RAISE NOTICE 'Failed to create user: %', SQLERRM;
    END;
    RETURN result_message;
END;
$$ LANGUAGE plpgsql;

-- Create stored procedure for updating a user
CREATE OR REPLACE FUNCTION update_user(
    p_id BIGINT,
    p_username VARCHAR,
    p_email VARCHAR,
    p_password VARCHAR
) RETURNS VARCHAR AS $$
BEGIN
    UPDATE Users
    SET username = p_username,
        email = p_email,
        password = p_password
    WHERE id = p_id;
    
    IF FOUND THEN
        RETURN 'User updated successfully';
    ELSE
        RETURN 'User with given ID not found';
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Create stored procedure for deleting a user
CREATE OR REPLACE FUNCTION delete_user(
    p_id BIGINT
) RETURNS VARCHAR AS $$
BEGIN
    DELETE FROM Users
    WHERE id = p_id;

    IF FOUND THEN
        RETURN 'User deleted successfully';
    ELSE
        RETURN 'User with given ID not found';
    END IF;
END;
$$ LANGUAGE plpgsql;
