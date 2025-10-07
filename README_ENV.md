# Environment Variables Setup

This project uses environment variables to store Google reCAPTCHA keys securely.

## Setup Instructions

1. **Copy the example file:**
   ```bash
   cp .env.example .env.local
   ```

2. **Update `.env.local` with your actual keys:**
   ```
   GOOGLE_RECAPTCHA_V2_SITE_KEY=your-v2-site-key
   GOOGLE_RECAPTCHA_V2_SECRET_KEY=your-v2-secret-key
   GOOGLE_RECAPTCHA_V3_SITE_KEY=your-v3-site-key
   GOOGLE_RECAPTCHA_V3_SECRET_KEY=your-v3-secret-key
   ```

## Running the Application

### Option 1: Using the run script (Recommended)

```bash
./run.sh
```

### Option 2: Manual environment variable export

```bash
export $(cat .env.local | grep -v '^#' | xargs) && mvn spring-boot:run
```

### Option 3: IDE Configuration

**IntelliJ IDEA:**

- A pre-configured run configuration "Spring Boot with Env" is available
- Find it in the run configurations dropdown (top right)
- Or manually: Go to Run > Edit Configurations > Add environment variables

**Default Values:**

- The application now includes default reCAPTCHA keys in `application.properties`
- You can run directly from IDE without additional configuration
- To use custom keys, set environment variables in your run configuration

**VS Code:**

1. Use the `dotenv` extension
2. Or configure `.vscode/launch.json` with env variables

## Testing

Access the following URLs:

- **v2 Registration:** http://localhost:8080/users/register?version=v2
- **v3 Registration:** http://localhost:8080/users/register?version=v3
- **View Users:** http://localhost:8080/users
- **H2 Console:** http://localhost:8080/h2-console

## Notes

- The `.env.local` file is gitignored for security
- Never commit real API keys to version control
- Use `.env.example` as a template for team members
